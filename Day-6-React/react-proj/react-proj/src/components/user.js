import React, {Fragment} from 'react';
import { Button, TextField, TableCell } from '@material-ui/core';
import UserDataContext from '../UserDataContext';

const User = ({user}) => {
    const [name, updateName] = React.useState(user.name);
    const [email, updateEmail] = React.useState(user.email);
    const [phone, updatePhone] = React.useState(user.phone.split(" ")[0]);

    const [nameEditable, changeNameEditable] = React.useState(false);
    const [emailEditable, changeEmailEditable] = React.useState(false);
    const [phoneEditable, changePhoneEditable] = React.useState(false);
    //const [updateBtnDisabled, toggleUpdateButton] = React.useState(true);
    
    // const handleUpdate = () => {
    //     changeNameEditable(false);
    //     changeEmailEditable(false);
    //     changePhoneEditable(false);
    //     toggleUpdateButton(true);
    // }

    return (
        <Fragment>
            <TableCell component="th" scope="row" align="center">{user.id}</TableCell>
            <TableCell align="center">
                {
                    nameEditable?
                        <TextField id="standard-basic" label="name" autoFocus value={name} 
                            onChange = {(e) => updateName(e.currentTarget.value)} 
                            onBlur = {() => changeNameEditable(false) } ></TextField>
                            :
                        <Button onClick={() => changeNameEditable(true)}>{name}</Button>
                }
            </TableCell>
            
            <TableCell align="center">
                {
                    emailEditable?
                        <TextField id="standard-basic" label="email" autoFocus value={email}
                         onChange = {(e) => updateEmail(e.currentTarget.value)}
                         onBlur = {() => changeEmailEditable(false)}></TextField>
                            :
                        <Button onClick={() => changeEmailEditable(true)}>{email}</Button>
                }
            </TableCell>

            <TableCell align="center">
                {
                    phoneEditable?
                        <TextField id="standard-basic" label="phone" autoFocus value={phone} 
                            onChange = {(e) => updatePhone(e.currentTarget.value)}
                            onBlur = {() => changePhoneEditable(false)}></TextField>
                            :
                        <Button onClick={() => changePhoneEditable(true)}>{phone}</Button>
                }
            </TableCell>
            {/* <TableCell><Button variant="contained" color="secondary" onClick={ handleUpdate } disabled = {updateBtnDisabled}>Update</Button></TableCell> */}
            <UserDataContext.Consumer>
                { context => 
                    <TableCell>
                        <Button variant="outlined" color="primary" href="#outlined-buttons" onClick = {() => {context.deleteUser(user.id)}}>
                            Delete
                        </Button>
                    </TableCell>
                }
            </UserDataContext.Consumer>
        </Fragment>
    );
}

export default User;