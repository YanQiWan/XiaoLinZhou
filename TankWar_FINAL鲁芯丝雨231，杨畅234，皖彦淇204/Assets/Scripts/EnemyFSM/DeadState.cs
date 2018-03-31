using UnityEngine;
using System.Collections;

public class DeadState : FSMState
{
    public DeadState() 
    {
        stateID = EnemyStateID.Dead;
    }

	public override void Reason(Transform player, GameObject npcObject)
    {

    }

	public override void Act(Transform player, GameObject npcObject)
    {
        //Do Nothing for the dead state
    }
}
