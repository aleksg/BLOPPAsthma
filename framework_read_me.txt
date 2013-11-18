Hva skal denne modulen gjøre?

- Bør kunne returnere .mp3-filene med instruksjoner
	mp3File getInstructions(TreatmentAction, childId)

- Bør kunne si til databasen fra at en medisin er tatt
	void medicineTaken(ChildId id, Date date, Medicine medicine)

- Bør kunne hente ut antallet credits et barn har mottatt
	int getCredits(childId)
- Hente ut health state
	HealthZone getHealthState(childId)




