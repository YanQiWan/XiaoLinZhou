using UnityEngine;
using System.Collections;

public abstract class AbstractFactory : MonoBehaviour{
	public abstract BulletProduct CreateBullet ();
	public abstract ArmorProduct CreateArmor ();
}
