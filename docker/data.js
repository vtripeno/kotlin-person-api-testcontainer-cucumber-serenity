db = db.getSiblingDB('mycollection');

db.person.insert(
  {
    "_id" : 1,
    "firstname" : "TEste",
    "lastname" : "USER"
  }
);