using UnityEngine;
using System.Collections;

public class EquipmentChoose : MonoBehaviour {
	void OnGUI(){
		if(GUI.Button(new Rect(100,100,128,65),"AgileTank")){
			PlayerTankController.tanktype=0;
			Application.LoadLevel("AdvancedFSM");
		}
		if(GUI.Button(new Rect(300,100,128,65),"AttackTank")){
			PlayerTankController.tanktype=1;
			Application.LoadLevel("AdvancedFSM");
		}	
		if(GUI.Button(new Rect(500,100,128,65),"DefenceTank")){
			PlayerTankController.tanktype=2;
			Application.LoadLevel("AdvancedFSM");
		}
		if(GUI.Button(new Rect(700,100,128,65),"SimpleTank")){
			PlayerTankController.tanktype=3;
			Application.LoadLevel("AdvancedFSM");
		}
	}
}
