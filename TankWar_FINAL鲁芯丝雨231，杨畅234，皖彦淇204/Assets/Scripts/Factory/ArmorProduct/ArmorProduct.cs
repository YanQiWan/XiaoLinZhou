using UnityEngine;
using System.Collections;

public abstract class ArmorProduct{
	private float defence;
	private float weight;
	ArmorProduct(){
	}
	public ArmorProduct(float defence,float weight){
		this.defence = defence;
		this.weight = weight;
	}
	public float getDefence()
	{
		return defence;
	}

	public float getWeight()
	{
		return weight;
	}
}
