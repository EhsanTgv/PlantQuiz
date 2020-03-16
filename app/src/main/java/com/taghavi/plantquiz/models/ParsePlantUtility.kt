package com.taghavi.plantquiz.models

import org.json.JSONObject

class ParsePlantUtility {
    fun parsePlantObjectsFromJsonData(search: String): List<Plant> {
        val allPlantsObject: ArrayList<Plant> = ArrayList()
        val downloadingObject = DownloadingObject()
        val topLevelPlantJsonData =
            downloadingObject.downloadJSONDataFrom("http://plantplaces.com/perl/mobile/flashcard.pl")
        val topLevelJsonObject = JSONObject(topLevelPlantJsonData)
        val plantObjectArray = topLevelJsonObject.getJSONArray("values")

        var index = 0

        while (index < plantObjectArray.length()) {
            val plantObject = Plant()
            val jsonObject = plantObjectArray.getJSONObject(index)

            with(jsonObject) {
                plantObject.genus = getString("genus")
                plantObject.species = getString("species")
                plantObject.cultivar = getString("cultivar")
                plantObject.common = getString("common")
                plantObject.pictureName = getString("picture_name")
                plantObject.description = getString("description")
                plantObject.difficulty = getInt("difficulty")
                plantObject.id = getInt("id")
            }

            allPlantsObject.add(plantObject)

            index++
        }
        return allPlantsObject
    }
}