using UnityEngine;
using System.Collections;

public class HighExplosiveBullet : BulletProduct {

	public HighExplosiveBullet(){
		damage = 80;
		Speed = 300.0f;
		LifeTime = 3.0f;
		Bullet = (GameObject)Resources.Load ("HighExplosiveBullet");
	}
}
