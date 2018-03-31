using UnityEngine;
using System.Collections;

public class AgileEquipmentFactory : AbstractFactory{
	public override BulletProduct CreateBullet (){
		return new ArmourPiercingBullet();
	}
	public override ArmorProduct CreateArmor (){
		return new NoneArmor ();
	}
}
