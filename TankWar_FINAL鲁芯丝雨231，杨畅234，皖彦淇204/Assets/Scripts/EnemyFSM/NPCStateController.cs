using UnityEngine;
using System.Collections;
using System.Collections.Generic;


public enum Transition
{
    None = 0,
    SawPlayer,
    ReachPlayer,
    LostPlayer,
    NoHealth,
}

public enum EnemyStateID
{
    None = 0,
    Patrolling,
    Chasing,
    Attacking,
    Dead,
}

public class NPCStateController : NPCAction 
{
    private List<FSMState> fsmStatelist;

    //The fsmStatelist are not changing directly but updated by using transitions
    private EnemyStateID currentStateID;
    public EnemyStateID CurrentStateID { get { return currentStateID; } }

    private FSMState currentState;
    public FSMState CurrentState { get { return currentState; } }

	public NPCStateController()
    {
        fsmStatelist = new List<FSMState>();
    }
	
    // Add New State into the list
    public void AddFSMState(FSMState fsmState)
    {
        // Check for Null reference before deleting
        if (fsmState == null)
        {
            Debug.LogError("FSM ERROR: Null reference is not allowed");
        }

        // First State inserted is also the Initial state
        //   the state the machine is in when the simulation begins
        if (fsmStatelist.Count == 0)
        {
            fsmStatelist.Add(fsmState);
            currentState = fsmState;
            currentStateID = fsmState.ID;
            return;
        }

        // Add the state to the List if it´s not inside it
        foreach (FSMState state in fsmStatelist)
        {
            if (state.ID == fsmState.ID)
            {
                Debug.LogError("FSM ERROR: Trying to add a state that was already inside the list");
                return;
            }
        }

        //If no state in the current then add the state to the list
        fsmStatelist.Add(fsmState);
    }
	
    public void DeleteState(EnemyStateID fsmState)
    {
        // Check for NullState before deleting
        if (fsmState == EnemyStateID.None)
        {
            Debug.LogError("FSM ERROR: bull id is not allowed");
            return;
        }

        // Search the List and delete the state if it´s inside it
        foreach (FSMState state in fsmStatelist)
        {
            if (state.ID == fsmState)
            {
                fsmStatelist.Remove(state);
                return;
            }
        }
        Debug.LogError("FSM ERROR: The state passed was not on the list. Impossible to delete it");
    }
	
    public void PerformTransition(Transition trans)
    {
        // Check for NullTransition before changing the current state
        if (trans == Transition.None)
        {
            Debug.LogError("FSM ERROR: Null transition is not allowed");
            return;
        }

        // Check if the currentState has the transition passed as argument
        EnemyStateID id = currentState.GetOutputState(trans);
        if (id == EnemyStateID.None)
        {
            Debug.LogError("FSM ERROR: Current State does not have a target state for this transition");
            return;
        }

        // Update the currentStateID and currentState		
        currentStateID = id;
        foreach (FSMState state in fsmStatelist)
        {
            if (state.ID == currentStateID)
            {
                currentState = state;
                break;
            }
        }
    }
}
