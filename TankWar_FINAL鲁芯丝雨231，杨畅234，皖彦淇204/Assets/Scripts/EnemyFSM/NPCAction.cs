using UnityEngine;
using System.Collections;

public class NPCAction : MonoBehaviour 
{
    //Player Transform
    protected Transform playerTransform;

    //Next destination position of the NPC Tank
    protected Vector3 destPos;

    //List of points for patrolling
    protected GameObject[] pointList;

    //Bullet shooting rate
    public float shootRate;
	public float elapsedTime;

	public float curRotSpeed;
	public float curSpeed;
	public float health;
    //Tank Turret
    public Transform turret { get; set; }
    public Transform bulletSpawnPoint { get; set; }

    protected virtual void Initialize() { }
    protected virtual void FSMUpdate() { }
    protected virtual void FSMFixedUpdate() { }
	
	void Start () 
    {
        Initialize();
	}

	void Update () 
    {
		FSMUpdate();//Update per frame
	}

    void FixedUpdate()
    {
        FSMFixedUpdate();
    }    
}
