using UnityEngine;
using System.Collections;

public class ChaseState : FSMState
{
    public ChaseState(Transform[] wp) 
    { 
        waypoints = wp;
        stateID = EnemyStateID.Chasing;
		
        //find next Waypoint position
        FindNextPoint();
    }

    public override void Reason(Transform player, GameObject npcObject)
    {
		Transform npc = npcObject.GetComponent<Transform> ();
        //Set the target position as the player position
        destPos = player.position;

        //Check the distance with player tank
        //When the distance is near, transition to attack state
        float dist = Vector3.Distance(npc.position, destPos);
        if (dist <= 200.0f)
        {
            Debug.Log("Switch to Attack state");
            npc.GetComponent<NPCTankController>().SetTransition(Transition.ReachPlayer);
        }
        //Go back to patrol is it become too far
        else if (dist >= 300.0f)
        {
            Debug.Log("Switch to Patrol state");
            npc.GetComponent<NPCTankController>().SetTransition(Transition.LostPlayer);
        }
    }

	public override void Act(Transform player, GameObject npcObject)
    {
		Transform npc = npcObject.GetComponent<Transform> ();
        //Rotate to the target point
        destPos = player.position;

        Quaternion targetRotation = Quaternion.LookRotation(destPos - npc.position);
		npc.rotation = Quaternion.Slerp(npc.rotation, targetRotation, Time.deltaTime * npcObject.GetComponent<NPCTankController>().curRotSpeed);

        //Go Forward
		npc.Translate(Vector3.forward * Time.deltaTime * npcObject.GetComponent<NPCTankController>().curSpeed);
    }
}
