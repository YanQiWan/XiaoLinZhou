using UnityEngine;
using System.Collections;

public class EnemyTank : NPCAction 
{
    public enum EnemyState
    {
		None,Patrol,Chase,Attack,Dead
	}
	
	public EnemyState currentState;
	private float currentSpeed;
    private float currentRotSpeed;
    public GameObject Bullet;
	
    private bool isDead;
//    private int health;

	
	protected override void Initialize () 
    {
		currentState = EnemyState.Patrol;
		currentSpeed = 150.0f;
		currentRotSpeed = 2.0f;
		isDead = false;
        elapsedTime = 0.0f;
        shootRate = 3.0f;
        health = 100;

        pointList = GameObject.FindGameObjectsWithTag("WandarPoint");
        FindNextPoint();//

        //Get the target enemy(Player)
        GameObject objPlayer = GameObject.FindGameObjectWithTag("Player");
        playerTransform = objPlayer.transform;

        if(!playerTransform)
            print("Player doesn't exist.. Please add one with Tag named 'Player'");

        turret = gameObject.transform.GetChild(0).transform;
        bulletSpawnPoint = turret.GetChild(0).transform;
	}

    //called when function Update id called
    protected override void FSMUpdate()
    {
		switch (currentState)
        {
		case EnemyState.Patrol: UpdatePatrolState(); break;
		case EnemyState.Chase: UpdateChaseState(); break;
		case EnemyState.Attack: UpdateAttackState(); break;
		case EnemyState.Dead: UpdateDeadState(); break;
        }

        elapsedTime += Time.deltaTime;

        if (health <= 0)
			currentState = EnemyState.Dead;
    }

    protected void UpdatePatrolState()
    {
        //Find another random patrol point if the current point is reached
        if (Vector3.Distance(transform.position, destPos) <= 100.0f)
        {
            print("Reached to the destination point\ncalculating the next point");
            FindNextPoint();
        }
		//Check the distance with player tank,When the distance is near, transition to chase state
        else if (Vector3.Distance(transform.position, playerTransform.position) <= 300.0f)
        {
            print("Switch to Chase Position");
            currentState = EnemyState.Chase;
        }
        //Rotate to the target point
        Quaternion targetRotation = Quaternion.LookRotation(destPos - transform.position);
        transform.rotation = Quaternion.Slerp(transform.rotation, targetRotation, Time.deltaTime * currentRotSpeed);  

        //Go Forward
        transform.Translate(Vector3.forward * Time.deltaTime * currentSpeed);
    }
	
    protected void UpdateChaseState()
    {
        //Set the target position as the player position
        destPos = playerTransform.position;

        //Check the distance with player tank
        //When the distance is near, transition to attack state
        float dist = Vector3.Distance(transform.position, playerTransform.position);
        if (dist <= 200.0f)
        {
			currentState = EnemyState.Attack;
        }
        //Go back to patrol is it become too far
        else if (dist >= 300.0f)
        {
            currentState = EnemyState.Patrol;
        }

        //Go Forward
        transform.Translate(Vector3.forward * Time.deltaTime * currentSpeed);
    }
	
    protected void UpdateAttackState()
    {
        //Set the target position as the player position
        destPos = playerTransform.position;

        //Check the distance with the player tank
        float dist = Vector3.Distance(transform.position, playerTransform.position);
        if (dist >= 200.0f && dist < 300.0f)
        {
            //Rotate to the target point
            Quaternion targetRotation = Quaternion.LookRotation(destPos - transform.position);
            transform.rotation = Quaternion.Slerp(transform.rotation, targetRotation, Time.deltaTime * currentRotSpeed);  

            //Go Forward
            transform.Translate(Vector3.forward * Time.deltaTime * currentSpeed);

            currentState = EnemyState.Attack;
        }
        //Transition to patrol is the tank become too far
        else if (dist >= 300.0f)
        {
			currentState = EnemyState.Patrol;
        }        

        //Always Turn the turret towards the player
        Quaternion turretRotation = Quaternion.LookRotation(destPos - turret.position);
		turret.rotation = Quaternion.Slerp(turret.rotation, turretRotation, Time.deltaTime * currentRotSpeed); 

        //Shoot the bullets
        ShootBullet();
    }
	
    protected void UpdateDeadState()
    {
        if (!isDead)
        {
            isDead = true;
            Explode();
        }
    }

	//Shoot
    private void ShootBullet()
    {
        if (elapsedTime >= shootRate)
        {
            //Shoot the bullet
            Instantiate(Bullet, bulletSpawnPoint.position, bulletSpawnPoint.rotation);
            elapsedTime = 0.0f;
        }
    }
	
	//Collision check
    void OnCollisionEnter(Collision collision)
    {
        //Reduce health
        if(collision.gameObject.tag == "Bullet")
            health -= collision.gameObject.GetComponent<Bullet>().damage;
    }   
	
    protected void FindNextPoint()
    {
        print("Finding next point");
        int rndIndex = Random.Range(0, pointList.Length);
        float rndRadius = 10.0f;
        
        Vector3 rndPosition = Vector3.zero;
        destPos = pointList[rndIndex].transform.position + rndPosition;

        //Check Range
        //Prevent to decide the random point as the same as before
        if (IsInCurrentRange(destPos))
        {
            rndPosition = new Vector3(Random.Range(-rndRadius, rndRadius), 0.0f, Random.Range(-rndRadius, rndRadius));
            destPos = pointList[rndIndex].transform.position + rndPosition;
        }
    }
	
    protected bool IsInCurrentRange(Vector3 pos)
    {
        float xPos = Mathf.Abs(pos.x - transform.position.x);
        float zPos = Mathf.Abs(pos.z - transform.position.z);

        if (xPos <= 50 && zPos <= 50)
            return true;

        return false;
    }

    protected void Explode()
    {
        float rndX = Random.Range(10.0f, 30.0f);
        float rndZ = Random.Range(10.0f, 30.0f);
        for (int i = 0; i < 3; i++)
        {
            rigidbody.AddExplosionForce(10000.0f, transform.position - new Vector3(rndX, 10.0f, rndZ), 40.0f, 10.0f);
            rigidbody.velocity = transform.TransformDirection(new Vector3(rndX, 20.0f, rndZ));
        }

        Destroy(gameObject, 1.5f);
    }

}
