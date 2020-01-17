import React from 'react';
import { TextField, Button, Container } from '@material-ui/core';
import UserDataContext from '../UserDataContext';

const AddUser = ({handleClose}) => {
    
    const [newName, updateName] = React.useState('');
    const [newEmail, updateEmail] = React.useState('');
    const [newPhone, updatePhone] = React.useState('');

    return(
        <UserDataContext.Consumer>
          { value => {
          return <Container>
              <TextField id="standard-basic" label="Name" value={newName} 
                onChange = {(e) => updateName(e.currentTarget.value)}>
              </TextField>
                <br/>
              <TextField id="standard-basic" type="email" label="Email" value={newEmail}
              onChange = {(e) => updateEmail(e.currentTarget.value)}>
              </TextField>
                <br/>
              <TextField id="standard-basic" type="number" label="Phone Number" value={newPhone}
              onChange = {(e) => updatePhone(e.currentTarget.value)}>
              </TextField>
              <br/> <br/>
              <Button variant="contained" color="primary" 
                onClick = {() => {value.onUserUpdateClicked({id: (value.numUsers()),name:newName, email:newEmail,phone:newPhone}); handleClose()}}>
                  Add User
              </Button>
          </Container>
          }}
        </UserDataContext.Consumer>
    );
};

export default AddUser;