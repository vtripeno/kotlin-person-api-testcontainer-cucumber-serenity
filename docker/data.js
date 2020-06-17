db = db.getSiblingDB('mycollection');

db.person.insert(
  {
    "_id" : "999",
    "firstName" : "Zé",
    "lastName" : "Mané"
  }
);