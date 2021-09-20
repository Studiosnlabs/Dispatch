const SignUpSelectVehicle = (props) => {
    return ( 
        <div className='SignupCheck'>
   

    <form className="signUpSelectVehicle">
        <label for="Motorbike">MotorBike</label><input type='checkbox' name="motorbike" value={props.getState('motorbike','')}></input>
        <label for="Car">Car</label><input type='checkbox' name="car" value={props.getState('car','')}></input>
        <label for="Pickup Truck">Pickup Truck</label><input type='checkbox' name="pickuptruck" value={props.getState('pickuptruck','')}></input> <br/>
      

        <label for="Bicycle">Bicycle</label><input type='checkbox'  name="bicycle" value={props.getState('bicycle','')}></input>
        <label for="Van">Van</label><input type='checkbox' name="van" value={props.getState('van','')}></input> 
        <label for="Caravan">Caravan</label><input type='checkbox'  name="caravan" value={props.getState('caravan','')}></input><br/>
    
        <button onClick={props.prev}>Previous</button>
        <button onClick={props.next}>Next</button>
    </form>
    </div> 
     );
}
 
export default SignUpSelectVehicle;