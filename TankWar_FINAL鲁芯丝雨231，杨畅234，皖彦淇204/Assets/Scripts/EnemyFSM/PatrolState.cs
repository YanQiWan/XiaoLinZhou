using UnityEngine;
using System.Collections;

public class PatrolState : FSMState
{
    public PatrolState(Transform[] wp) 
    { 
        waypoints = wp;
        stateID = EnemyStateID.Patrolling;

    }

	public override void Reason(Transform player, GameObject npcObject)
    {
		Transform npc = npcObject.GetComponent<Transform> ();
        //Check the distance with player tank
        //When the distance is near, transition to chase state
        if (Vector3.Distance(npc.position, player.position) <= 300.0f)
        {
            Debug.Log("Switch to Chase State");
            npc.GetComponent<NPCTankController>().SetTransition(Transition.SawPlayer);
        }
    }

	public override void Act(Transform player, GameObject npcObject)
    {
		Transform npc = npcObject.GetComponent<Transform> ();
        //Find another random patrol point if the current point is reached
		
        if (Vector3.Distance(npc.position, destPos) <= 100.0f)
        {
            Debug.Log("Reached to the destination point\ncalculating the next point");
            FindNextPoint();
        }

        //Rotate to the target point
        Quaternion targetRotation = Quaternion.LookRotation(destPos - npc.position);
		npc.rotation = Quaternion.Slerp(npc.rotation, targetRotation, Time.deltaTime * npcObject.GetComponent<NPCTankController>().curRotSpeed);

        //Go Forward
		npc.Translate(Vector3.forward * Time.deltaTime * npcObject.GetComponent<NPCTankController>().curSpeed);
    }
}