function Car(make, engineNumber, engineVariant, fuelType){
    this.make = make || '';
    this.engineNumber = engineNumber || '0';
    this.engineVariant = engineVariant || 'BS-IV';
    this.fuelType = fuelType || 'PETROL';
}

function Maruti(engineNumber, engineVariant, fuelType, model){
    Car.call(this, "Maruti", engineNumber, engineVariant, fuelType);
    this.model = model;
}

//INHERITING
Maruti.prototype = new Car();
Maruti.prototype.constructor = Maruti;

function Honda(engineNumber, engineVariant, fuelType, model){
    Car.call(this, "Honda", engineNumber, engineVariant, fuelType);
    this.model = model;
}

//INHERITING
Honda.prototype = new Car();
Honda.prototype.constructor = Honda;

//NEW KEYWORD
let car1 = new Maruti("M123", "BS-VI", "Diesel","Sx4");
let car2 = new Honda("H212", "BS-IV", "Petrol", "Civic");

console.log('CAR 1');
console.log(car1.make + " " + car1.model + " " + car1.fuelType + " " + car1.engineVariant + " " + car1.engineNumber);

console.log('CAR 2');
console.log(car2.make + " " + car2.model + " " + car2.fuelType + " " + car2.engineVariant + " " + car2.engineNumber);
