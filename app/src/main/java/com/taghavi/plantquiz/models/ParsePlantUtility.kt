package com.taghavi.plantquiz.models

class ParsePlantUtility {
    fun parsePlantObjectsFromJsonData(search: String): List<Plant> {
        val allPlantsObject :ArrayList<Plant> = ArrayList()
        val downloadingObject = DownloadingObject().downloadJSONDataFrom("http://plantplaces.com/perl/mobile/flashcard.pl")

        return  allPlantsObject
    }
}