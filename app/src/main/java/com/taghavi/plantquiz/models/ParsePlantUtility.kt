package com.taghavi.plantquiz.models

import org.json.JSONObject

class ParsePlantUtility {
    fun parsePlantObjectsFromJsonData(search: String): List<Plant> {
        val allPlantsObject: ArrayList<Plant> = ArrayList()
        val downloadingObject = DownloadingObject()
        val topLevelPlantJsonData =
            DownloadingObject().downloadJSONDataFrom("http://plantplaces.com/perl/mobile/flashcard.pl")
        val topLevelJsonObject = JSONObject(topLevelPlantJsonData)
        val plantObjectArray = topLevelJsonObject.getJSONArray("values")

        return allPlantsObject
    }
}