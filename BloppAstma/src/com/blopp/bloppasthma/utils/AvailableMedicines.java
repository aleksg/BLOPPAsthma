package com.blopp.bloppasthma.utils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This is a helper class to get the available medicines in the database.
 * Thus, medcinesMap must always reflect the exact fields in the database. If not,
 * medicines will not appear in any lists.
 */
public class AvailableMedicines
{
	public HashMap<String, Integer> medicinesMap;
	
	public AvailableMedicines()
	{
		medicinesMap = new HashMap<String, Integer>();
		medicinesMap.put("Flutide", 1); //Flutide
		medicinesMap.put("Ventoline", 2); //Ventoline
		medicinesMap.put("Seretide", 3); //Seretide
	}
	public List<String> getAllMedicineNames(){
		List<String> medicines = new ArrayList<String>();
		for (String string : medicinesMap.keySet())
		{
			medicines.add(string);
		}
		return medicines;
		
	}
	public int getMedicineByName(String name)
	{
		for (String s : medicinesMap.keySet())
		{
			if(s.equalsIgnoreCase(name))
			{
				return medicinesMap.get(s);
			}
		}
		throw new IllegalArgumentException("The name of this medicine does not exist");
	}
	public String getMedicineById(int id)
	{
		for (String s : medicinesMap.keySet())
		{
			if(id==medicinesMap.get(s))
			{
				return s;
			}
		}
		throw new IllegalArgumentException("The id does not exist");
	}
}

