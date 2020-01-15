
db = db.getSiblingDB("AUAsst");

//CREATE COLLECTION
db.createCollection("games");

//ADDING 5 GAMES
db.games.insertMany([
	{
		name: "NFS Most Wanted",
		genre: "Racing",
		rating: 90	
	},
	{
		name: "GTA San andreas",
		genre: "Action-adventure",
		rating: 85	
	},
	{
		name: "Counter Strike 1.6",
		genre: "Action",
		rating: 95
	},
	{
		name: "Call of Duty: Black Ops",
		genre: "Action",
		rating: 97
	},
	{
		name: "Hitman Blood Money",
		genre: "Action",
		rating: 80
	}
]);


//RETURN ALL GAMES
cursor = db.games.find();
while ( cursor.hasNext() ) {
   printjson( cursor.next() );
}

//FINDING ONE USING LIMIT()
printjson(db.games.find({name: 'NFS Most Wanted'}).limit(1));

//FINDING ONE USING FINDONE()
printjson(db.games.findOne({name: 'NFS Most Wanted'}))

//THREE HIGHEST RATED GAMES
cursor = db.games.find().sort({rating:-1}).limit(3);
while (cursor.hasNext()){
    printjson(cursor.next());
}

//UPDATING USING UPDATE()
db.games.update(
	{$or: [{name: "GTA San andreas"}, {name: "Counter Strike 1.6"}]},
	{
		$set: { achievements: ["Game Master", "Speed Daemon"] }
	},
	{ multi: true }
);

//REMOVING TO RE-UPDATE WITH SAVE
db.games.update(
	{},
	{
		$unset: {achievements: 1}
	},
	{multi: true}
);

//UPDATING USING SAVE()
cursor = db.games.find({$or: [{name: "GTA San andreas"},{name: "Counter Strike 1.6"}]});
while(cursor.hasNext()){
    var doc = cursor.next();
    doc.achievements = ["Game Master", "Speed Daemon"];
    db.games.save(doc);
}

// GAMES THAT HAVE BOTH ‘Game Maser’ AND ‘Speed Daemon’ IN ACHIEVEMENTS
printjson(db.games.find({achievements: {$all: ["Game Master","Speed Daemon"]}}));

// GAMES HAVING ACHIEVEMENTS
printjson(db.games.find({achievements: {$exists: true}}));