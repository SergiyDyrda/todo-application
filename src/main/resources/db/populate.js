use todoboard;
db.tickets.deleteMany({});
db.tickets.insertMany( [
    { name: "develop app", description: "write test CRUD application", status: "TODO" },
    { name: "wash hands", description: "get clean or die", status: "TODO" },
    { name: "change shit", description: "changing something", status: "IN_PROGRESS" },
    { name: "eat", description: "make dinner and eat", status: "IN_PROGRESS" },
    { name: "Chuck Norris", description: "Chuck Norris", status: "COMPLETED" }
] );