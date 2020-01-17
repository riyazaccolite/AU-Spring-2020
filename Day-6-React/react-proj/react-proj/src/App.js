import React, { useEffect } from 'react';
import './App.css';
import ButtonAppBar from './components/app-bar';
import UserList from './components/user-list';
import { Container } from '@material-ui/core';
import UserDataContext from './UserDataContext';
import * as lodashArray from 'lodash/array';
import * as lodashCollection from 'lodash/collection';

function App() {
  
  const usersData = []
  const [users, updateUsers] = React.useState(usersData);
  const [newId, updateNewId] = React.useState(0);

  const [sortFlags, updateSortFlags] = React.useState({
    id:0,
    email:0,
    phone:0,
    name:0
  });
  
  const onUserUpdateClicked = user => {
    updateUsers([...users,user]);
    updateNewId(newId + 1);
  }
  
  const getNumUsers = () => newId;

  const deleteUser = (id) => updateUsers(lodashArray.remove(users,(user) => user.id !== id ));

  const sortUsersBy = (parameter) => {
      let temp = lodashCollection.sortBy(users, ob => ob[parameter]);
      
      if(sortFlags[parameter] === 0)
        sortFlags[parameter] = 1;
      else{
        temp = lodashArray.reverse(temp);
        sortFlags[parameter] = 0;
      }
      updateUsers(temp);
      updateSortFlags(sortFlags);
  }

  //ComponentDidMount
  useEffect(() => {
    setTimeout(() => {
    fetch('https://jsonplaceholder.typicode.com/users')
      .then( response => response.json())
      .then((response) => {updateNewId(response.length + 1); return response;})
      .then(updateUsers)
      .catch( err => {});
    },2000);
  },[]);
  
  const contextData = {onUserUpdateClicked: onUserUpdateClicked, numUsers: getNumUsers, deleteUser: deleteUser};
  
  return (
    <UserDataContext.Provider value={contextData}>
      <div>
        <ButtonAppBar/>
        <Container>
          { users.length? 
              <UserList users = {users} sortUsersBy = {sortUsersBy}/>
                :
              <h1>Loading..</h1>
          }
        </Container>
      </div>
    </UserDataContext.Provider>
  );
}

export default App;
