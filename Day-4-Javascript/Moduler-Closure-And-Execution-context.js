var myModule = (function(){
    let _registrations = 0;
    let _users = []

    function _updateRegistrations(){ this.registrations++; } //Closure

    function _User(firstName, lastName, age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    function _print(){ 
        for(let key in this)
            console.log(key + ": " + this[key]);
        console.log();
    }
    
    function _changeUser(firstName, lastName, age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    function addUser(firstName, lastName, age){ 
        let user = new _User(firstName, lastName, age);

        _users.push(user); //CLOSURE

        _updateRegistrations();
        return user;
    }

    function printUser(user){
        _print.call(user); // CALL
    }

    function updateUser(user,userDetails){ //APPLY
        _changeUser.apply(user,userDetails);
    }

    function addField(field,value){ //BIND
        this[field] = value;
    }

    return {
        addUser: addUser,
        addField: addField,
        updateUser: updateUser,
        printUser: printUser,
    }
})();

let user1 = myModule.addUser("Harshit","C",21);
myModule.printUser(user1);

myModule.updateUser(user1,["Harshit", "Chhabra", 21]);
myModule.printUser(user1);

let myUpdater = myModule.addField.bind(user1);
myUpdater("College", "Vasavi College of Engineering");
myModule.printUser(user1);

myUpdater("ThisAssignment", "Javascript");
myModule.printUser(user1);