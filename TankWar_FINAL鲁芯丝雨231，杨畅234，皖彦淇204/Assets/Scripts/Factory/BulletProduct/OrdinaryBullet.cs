using UnityEngine;
using System.Collections;

public class OrdinaryBullet: BulletProduct {

	public OrdinaryBullet(){
		damage = 40;
		Speed = 600.0f;
		LifeTime = 3.0f;
		Bullet = (GameObject)Resources.Load ("OrdinaryBullet");
	}
}
