package scripts.scarPlanks;

import org.tribot.api.Clicking;
import org.tribot.api.General;
import org.tribot.api.Timing;
import org.tribot.api.types.generic.Condition;
import org.tribot.api.util.ABCUtil;
import org.tribot.api2007.Banking;
import org.tribot.api2007.Game;
import org.tribot.api2007.Interfaces;
import org.tribot.api2007.Inventory;
import org.tribot.api2007.Options;
import org.tribot.api2007.Player;
import org.tribot.api2007.types.RSArea;
import org.tribot.api2007.types.RSItem;
import org.tribot.api2007.types.RSTile;

import scripts.ScarAPI.Utility.PriceChecker;

public class PlankValues {
	
    public static String planks;
    public static int planksMade;
    public static int plankInt;
    public static String logs;
    public static int[] potionIds;
	public static RSArea bankArea = new RSArea(new RSTile[] { new RSTile(3250, 3420, 0), new RSTile(3257, 3419, 0), new RSTile(3257, 3420, 0), new RSTile(3254, 3421, 0), new RSTile(3254, 3423, 0), new RSTile(3252, 3423, 0), new RSTile(3250, 3422, 0) });
	public static RSArea plankArea = new RSArea(new RSTile[] { new RSTile(3300, 3491, 0), new RSTile(3304, 3491, 0), new RSTile(3304, 3487, 0), new RSTile(3300, 3487, 0), new RSTile(3300, 3488, 0), new RSTile(3301, 3488, 0), new RSTile(3301, 3490, 0), new RSTile(3300, 3490, 0) });
    public static boolean guiComplete = false;
    public static boolean usePotions;
    public static int interfaceChild;
    public static ABCUtil abc = new ABCUtil();
    public static boolean runScript = true;
    
    public static boolean atBank(){
    	return bankArea.contains(Player.getPosition());
    }
    
    public static boolean atSawMill() {
		return plankArea.contains(Player.getPosition());
	}
    
    public static boolean hasLogs(){
        return Inventory.getCount(logs) > 0;
    }
    
    public static boolean hasPlanks(){
    	return Inventory.getCount(planks) > 0;
    }
 
    public static boolean hasPotions(){
        return Inventory.find(potionIds).length > 0;
    }
    
	public static int plankprice(){
		return PriceChecker.getOSbuddyPrice(plankInt);
    }

	public static boolean drinkPotion() {
        if(!Banking.isBankScreenOpen() && Interfaces.get(403) == null){
            RSItem staminaPot[] = Inventory.find(potionIds);
            Clicking.click(staminaPot);
            Timing.waitCondition(new Condition() {
                @Override
                public boolean active() {
                    General.sleep(25, 50);
                    return Game.getSetting(638) > 0;
                }
            }, General.random(2000, 5000));
            Options.setRunOn(true);
            return true;
        }
        return false;
	}
}
