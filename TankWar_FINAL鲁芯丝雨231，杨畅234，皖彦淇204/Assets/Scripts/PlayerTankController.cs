using UnityEngine;
using System.Collections;

public class PlayerTankController : MonoBehaviour
{
	public static int tanktype;
	public AudioClip fireaudio;
	public AudioClip behitaudio;
	private BulletProduct bulletfactory;
	private ArmorProduct Armor;
	
    public Transform Turret;
    public Transform bulletSpawnPoint; 
	public GUIText HP;
    private float curSpeed, targetSpeed, rotSpeed;
    private float turretRotSpeed = 1.0f;
    private float maxForwardSpeed = 300.0f;
    private float maxBackwardSpeed = -50.0f;
	private float health = 100.0f;
    //Bullet shooting rate
	protected float shootRate = 0.5f;
	protected float elapsedTime = 2.0f;

	void initializeEquipment(AbstractFactory af){
		bulletfactory = af.CreateBullet ();
		Armor = af.CreateArmor ();
	}

    void Start()
    {
        //Tank Settings
        rotSpeed = 150.0f;
		switch (tanktype) {
		case 0:initializeEquipment (new AgileEquipmentFactory());break;		
		case 1:initializeEquipment (new AttackEquipmentFactory());break;
		case 2:initializeEquipment(new DefenceEquipmentFactory());break;
		case 3:initializeEquipment(new SimpleEquipmentFactory());break;	
		}

		maxForwardSpeed -= Armor.getWeight ();
		health += Armor.getDefence ();
		HP.text = "HP:" + health.ToString ();
    }

    void OnEndGame()
    {
        // Don't allow any more control changes when the game ends
        this.enabled = false;
    }

    void Update()
    {
        UpdateControl();
        UpdateWeapon();
		HP.text = "HP:" + health.ToString ();
    }
    
    void UpdateControl()
    {
        //AIMING WITH THE MOUSE
        // Generate a plane that intersects the transform's position with an upwards normal.
		//shu biao xuan zhuan kong zhi shi jiao
        Plane playerPlane = new Plane(Vector3.up, transform.position + new Vector3(0, 0, 0));

        // Generate a ray from the cursor position
        Ray RayCast = Camera.main.ScreenPointToRay(Input.mousePosition);

        // Determine the point where the cursor ray intersects the plane.
        float HitDist = 0;

        // If the ray is parallel to the plane, Raycast will return false.
        if (playerPlane.Raycast(RayCast, out HitDist))
        {
            // Get the point along the ray that hits the calculated distance.
            Vector3 RayHitPoint = RayCast.GetPoint(HitDist);

            Quaternion targetRotation = Quaternion.LookRotation(RayHitPoint - transform.position);
            Turret.transform.rotation = Quaternion.Slerp(Turret.transform.rotation, targetRotation, Time.deltaTime * turretRotSpeed);
        }
		//fang wei kong zhi 
        if (Input.GetKey(KeyCode.W))
        {
            targetSpeed = maxForwardSpeed;
        }
        else if (Input.GetKey(KeyCode.S))
        {
            targetSpeed = maxBackwardSpeed;
        }
        else
        {
            targetSpeed = 0;
        }

        if (Input.GetKey(KeyCode.A))
        {
            transform.Rotate(0, -rotSpeed * Time.deltaTime, 0.0f);
        }
        else if (Input.GetKey(KeyCode.D))
        {
            transform.Rotate(0, rotSpeed * Time.deltaTime, 0.0f);
        }

        //Determine current speed
        curSpeed = Mathf.Lerp(curSpeed, targetSpeed, 7.0f * Time.deltaTime); //dang qian su du que ding    lerp shi cha zhi 
        transform.Translate(Vector3.forward * Time.deltaTime * curSpeed);    //transform ji he shu xing de bian huan   gaibian weizhi jiaodu he size
    }
	// wuqi gengxin  gaibian zidan
    void UpdateWeapon()
    {
		elapsedTime+=Time.deltaTime;
        if(Input.GetMouseButtonDown(0))
        {
            if (elapsedTime >= shootRate)
            {
                //Reset the time
                elapsedTime = 0.0f;

                //Also Instantiate over the PhotonNetwork
                Instantiate(bulletfactory.Bullet, bulletSpawnPoint.position, bulletSpawnPoint.rotation);
				audio.PlayOneShot(fireaudio);
            }
        }
    }

	// Check the collision with the bullet system function
	void OnCollisionEnter(Collision collision)
	{
		//Reduce health
		if (collision.gameObject.tag == "Bullet")
		{
			health -= collision.gameObject.GetComponent<BulletProduct>().damage;
			audio.PlayOneShot(behitaudio);
			if (health <= 0)
			{
				Explode();
				Application.LoadLevel("GameOverSence");  //jiazai xin changjing 
			}
		}
	}
	
	protected void Explode()   
	{
		float rndX = Random.Range(10.0f, 30.0f);
		float rndZ = Random.Range(10.0f, 30.0f);
		for (int i = 0; i < 3; i++)
		{
			rigidbody.AddExplosionForce(10000.0f, transform.position - new Vector3(rndX, 10.0f, rndZ), 40.0f, 10.0f);  //jia baozha li  ta chao na bao zha
			rigidbody.velocity = transform.TransformDirection(new Vector3(rndX, 20.0f, rndZ));  //yanzhe bao zha li fangxiang baozha 
		}
		Destroy(gameObject, 1.5f);
	}
}