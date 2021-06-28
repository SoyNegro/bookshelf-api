package com.thedarksideofcode.service

import com.thedarksideofcode.DB
import com.thedarksideofcode.models.Classification
import org.litote.kmongo.eq

class ClassificationService {

  suspend fun getClassificationById(id:String): Classification? {
      return DB.classificationCollection.findOne(Classification::classificationID eq id)
  }

  suspend fun saveClassification(classification: Classification){
      DB.classificationCollection.save(classification)
  }

  suspend fun updateClassificationById(classification: Classification){
      DB.classificationCollection.updateOne(Classification::classificationID eq classification.classificationID,classification)
  }

  suspend fun deleteClassificationById(id: String){
      DB.classificationCollection.deleteOne(Classification::classificationID eq id)
  }


}
