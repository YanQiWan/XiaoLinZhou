using UnityEngine;
using System.Collections;
using System.Collections.Generic;

public abstract class FSMState
{
    protected Dictionary<Transition, EnemyStateID> map = new Dictionary<Transition, EnemyStateID>();
    protected EnemyStateID stateID;
    public EnemyStateID ID { get { return stateID; } }
    protected Vector3 destPos;
    protected Transform[] waypoints;

    public void AddTransition(Transition transition, EnemyStateID id)
    {
        // Check if anyone of the args is invallid
        if (transition == Transition.None || id == EnemyStateID.None)
        {
            Debug.LogWarning("FSMState : Null transition not allowed");
            return;
        }

        //Since this is a Deterministc FSM,
        //Check if the current transition was already inside the map
        if (map.ContainsKey(transition))
        {
            Debug.LogWarning("FSMState ERROR: transition is already inside the map");
            return;
        }

        map.Add(transition, id);
        Debug.Log("Added : " + transition + " with ID : " + id);
    }

    //delete a pair transition-state from this state´s map.
    public void DeleteTransition(Transition trans)
    {
        // Check for NullTransition
        if (trans == Transition.None)
        {
            Debug.LogError("FSMState ERROR: NullTransition is not allowed");
            return;
        }

        // Check if the pair is inside the map before deleting
        if (map.ContainsKey(trans))
        {
            map.Remove(trans);
            return;
        }
        Debug.LogError("FSMState ERROR: Transition passed was not on this State´s List");
    }

	
    //return the new state the FSM should be if its state receives a transition
    public EnemyStateID GetOutputState(Transition trans)
    {
        // Check for NullTransition
        if (trans == Transition.None)
        {
            Debug.LogError("FSMState ERROR: NullTransition is not allowed");
            return EnemyStateID.None;
        }

        // Check if the map has this transition
        if (map.ContainsKey(trans))
        {
            return map[trans];
        }

        Debug.LogError("FSMState ERROR: " + trans+ " Transition passed to the State was not on the list");
        return EnemyStateID.None;
    }
	
    // Decides if the state should transition to another on its list
    public abstract void Reason(Transform player, GameObject npc);
	
	//This method controls the behavior of the NPC in the game World.
	public abstract void Act(Transform player, GameObject npc);
	
    // Find the next semi-random patrol point
    public void FindNextPoint()
    {
        //Debug.Log("Finding next point");
        int rndIndex = Random.Range(0, waypoints.Length);
        Vector3 rndPosition = Vector3.zero;
        destPos = waypoints[rndIndex].position + rndPosition;
    }
	
    /// Check whether the next random position is the same as current tank position
    protected bool IsInCurrentRange(Transform trans, Vector3 pos)
    {
        float xPos = Mathf.Abs(pos.x - trans.position.x);
        float zPos = Mathf.Abs(pos.z - trans.position.z);

        if (xPos <= 50 && zPos <= 50)
            return true;

        return false;
    }
}
