using UnityEngine;
using System.Collections;

public class NPCTankController : NPCStateController
{
	private BulletProduct bulletfactory;
	private ArmorProduct Armor;

	public AudioClip fireaudio;
	public AudioClip behitaudio;
	public AudioClip explosionaudio;

    //Initialize the Finite state machine for the NPC tank
    protected override void Initialize()
    {
        health = 100;

        elapsedTime = 0.0f;
        shootRate = 2.0f;
		curRotSpeed = 1.0f;
		curSpeed = 300.0f;

        //Get the target enemy(Player)
        GameObject objPlayer = GameObject.FindGameObjectWithTag("Player");
        playerTransform = objPlayer.transform;

        if (!playerTransform)
            print("Player doesn't exist.. Please add one with Tag named 'Player'");

        //Get the turret of the tank
        turret = gameObject.transform.GetChild(0).transform;
        bulletSpawnPoint = turret.GetChild(0).transform;

		int randomIndex=Random.Range(0,2);
		AbstractFactory af;
		switch (randomIndex) {
		case 0:	af=	new DefenceEquipmentFactory();break;
		case 1: af=new AttackEquipmentFactory();break;
		case 2: af=new AgileEquipmentFactory();break;
		default:af=new SimpleEquipmentFactory();break;
		}
		Debug.LogWarning("randomIndex="+randomIndex);
		initializeEquipment (af);
		curSpeed -= Armor.getWeight ();
		health += Armor.getDefence ();
        //Start Doing the Finite State Machine
        ConstructFSM();
    }

    //Update each frame
    protected override void FSMUpdate()
    {
        //Check for health
        elapsedTime += Time.deltaTime;
    }

	void initializeEquipment(AbstractFactory af){
		bulletfactory = af.CreateBullet ();
		Armor = af.CreateArmor ();
	}

    protected override void FSMFixedUpdate()
    {
        CurrentState.Reason(playerTransform, gameObject);
		CurrentState.Act(playerTransform, gameObject);
    }

    public void SetTransition(Transition t) 
    { 
        PerformTransition(t); 
    }

    private void ConstructFSM()
    {
        //Get the list of points
        pointList = GameObject.FindGameObjectsWithTag("WandarPoint");

        Transform[] waypoints = new Transform[pointList.Length];
        int i = 0;
        foreach(GameObject obj in pointList)
        {
            waypoints[i] = obj.transform;
            i++;
        }

        PatrolState patrol = new PatrolState(waypoints);
        patrol.AddTransition(Transition.SawPlayer, EnemyStateID.Chasing);
        patrol.AddTransition(Transition.NoHealth, EnemyStateID.Dead);

        ChaseState chase = new ChaseState(waypoints);
        chase.AddTransition(Transition.LostPlayer, EnemyStateID.Patrolling);
        chase.AddTransition(Transition.ReachPlayer, EnemyStateID.Attacking);
        chase.AddTransition(Transition.NoHealth, EnemyStateID.Dead);

        AttackState attack = new AttackState(waypoints);
        //attack.AddTransition(Transition.LostPlayer, EnemyStateID.Patrolling);
        attack.AddTransition(Transition.SawPlayer, EnemyStateID.Chasing);
        attack.AddTransition(Transition.NoHealth, EnemyStateID.Dead);

        DeadState dead = new DeadState();
        dead.AddTransition(Transition.NoHealth, EnemyStateID.Dead);

        AddFSMState(patrol);
        AddFSMState(chase);
        AddFSMState(attack);
        AddFSMState(dead);
    }
	
    // Check the collision with the bullet
    void OnCollisionEnter(Collision collision)
    {
        //Reduce health
        if (collision.gameObject.tag == "Bullet")
        {
			health -= collision.gameObject.GetComponent<BulletProduct>().damage;
			audio.PlayOneShot(behitaudio);
            if (health <= 0)
            {
                Debug.Log("Switch to Dead State");
                SetTransition(Transition.NoHealth);
                Explode();
            }
        }
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
		audio.PlayOneShot (explosionaudio);
        Destroy(gameObject, 1.5f);
    }
	
    public void ShootBullet()
    {
        if (elapsedTime >= shootRate)
        {
			Instantiate(bulletfactory.Bullet, bulletSpawnPoint.position, bulletSpawnPoint.rotation);
            elapsedTime = 0.0f;
			audio.PlayOneShot(fireaudio);
        }
    }
}
