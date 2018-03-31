using UnityEngine;
using System.Collections;

public class ArmourPiercingBullet : BulletProduct {
	public ArmourPiercingBullet(){
		damage = 20;
		Speed = 1600.0f;
		LifeTime = 3.0f;
		Bullet = (GameObject)Resources.Load ("ArmourPiercingBullet");
	}

}
