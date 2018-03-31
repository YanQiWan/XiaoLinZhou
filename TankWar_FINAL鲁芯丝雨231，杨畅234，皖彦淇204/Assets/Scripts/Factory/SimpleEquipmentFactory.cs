using UnityEngine;
using System.Collections;

public class SimpleEquipmentFactory : AbstractFactory {

	public override BulletProduct CreateBullet (){
		return new OrdinaryBullet ();
	}
	public override ArmorProduct CreateArmor (){
		return new LightArmor ();
	}
}
