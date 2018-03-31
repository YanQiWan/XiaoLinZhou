using UnityEngine;
using System.Collections;

public abstract class BulletProduct : MonoBehaviour {

	public GameObject Explosion;
	public GameObject Bullet;
	
	public float Speed;
	public float LifeTime;
	public int damage;
	
	void Start()
	{
		Destroy(gameObject, LifeTime);
	}
	
	void Update()
	{
		transform.position += 
			transform.forward * Speed * Time.deltaTime;       
	}
	
	void OnCollisionEnter(Collision collision)
	{
		ContactPoint contact = collision.contacts[0];
		Instantiate(Explosion, contact.point, Quaternion.identity);
		Destroy(gameObject);
	}
}
