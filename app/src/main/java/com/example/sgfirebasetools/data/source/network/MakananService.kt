package com.example.sgfirebasetools.data.source.network

import com.example.sgfirebasetools.data.source.model.MakananResponse
import com.example.sgfirebasetools.data.source.network.Constant.COLLECTION_MAKANAN
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MakananService {
    private val db = Firebase.firestore

    fun getAllMakanan() = db.collection(COLLECTION_MAKANAN).get()
    fun getMakanan(documentId: String) =
        db.collection(COLLECTION_MAKANAN).document(documentId).get()

    fun addPokemon(model: MakananResponse) = db.collection(COLLECTION_MAKANAN).add(model)
}