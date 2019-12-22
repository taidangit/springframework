import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import Dashboard from './components/Dashboard';
import Header from './components/Layout/Header';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';

import AddNewProject from './components/Project/AddNewProject';
import UpdateProject from './components/Project/UpdateProject';

import { Provider } from 'react-redux';
import store from './store';
import ProjectBoard from './components/ProjectBoard/ProjectBoard';
import AddProjectTask from './components/ProjectBoard/ProjectTask/AddProjectTask';
import UpdateProjectTask from './components/ProjectBoard/ProjectTask/UpdateProjectTask';
import Landing from './components/Layout/Landing';
import Login from './components/UserManagement/Login';
import Register from './components/UserManagement/Register';
import jwt_decode from 'jwt-decode';
import { setJWTToken } from './securityUltis/setJWTToken';
import * as types from './actions/types';
import { logout } from './actions/securityActions';
import UpdateUserInfo from './components/UserManagement/UpdateUserInfo';
import SecuredRoute from './securityUltis/SecureRoute';
import viewProductTask from './components/ProjectBoard/ProjectTask/viewProductTask';

const jwtToken = localStorage.getItem("jwtToken");

if(jwtToken) {
  setJWTToken(jwtToken);
  const decoded_jwtToken = jwt_decode(jwtToken);
  store.dispatch({
    type: types.SET_CURRENT_USER,
    payload: decoded_jwtToken
  });

  const currentTime = Date.now()/1000;
  if(decoded_jwtToken.exp < currentTime) {
    //handle logout
    store.dispatch(logout());
    window.location.href = "/";
  }
}


class App extends Component {
  render() {
    return (
      <Provider store={store}>
        <Router>
          <React.Fragment>
            <Header />
            <br/><br/><br/>

            <Switch>
              <Route exact path="/" component={Landing} />
              <Route exact path="/login" component={Login} />
              <Route exact path="/register" component={Register} />
            </Switch>

            <Switch>
              <SecuredRoute exact path="/dashboard" component={Dashboard} />
              <SecuredRoute exact path="/addNewProject" component={AddNewProject} />
              <SecuredRoute exact path="/updateProject/:id" component={UpdateProject} />
              <SecuredRoute exact path="/projectBoard/:id" component={ProjectBoard} />
              <SecuredRoute exact path="/addProjectTask/:id" component={AddProjectTask} />
              <SecuredRoute exact path="/updateProjectTask/:id1/:id2" component={UpdateProjectTask} />
              <SecuredRoute exact path="/viewProjectTask/:id1/:id2" component={viewProductTask} />
              <SecuredRoute exact path="/updateUserInfo" component={UpdateUserInfo} />
            </Switch>
            
          </React.Fragment>
        </Router>
      </Provider>
      
      
    );
  }
  
}

export default App;
