using UnityEngine;
using System.Collections;

public class AttackEquipmentFactory : AbstractFactory{

	public override BulletProduct CreateBullet (){
		return new HighExplosiveBullet ();
	}
	public override ArmorProduct CreateArmor (){
		return new LightArmor ();
	}
}
