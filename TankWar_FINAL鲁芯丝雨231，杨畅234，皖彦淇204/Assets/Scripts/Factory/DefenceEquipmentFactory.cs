using UnityEngine;
using System.Collections;

public class DefenceEquipmentFactory : AbstractFactory {

	public override BulletProduct CreateBullet (){
		return new OrdinaryBullet ();
	}
	public override ArmorProduct CreateArmor (){
		return new HeavyArmor ();
	}
}
