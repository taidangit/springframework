import { combineReducers } from 'redux';
import errorReducer from './errorReducer';
import projectReducer from './projectReducer';
import projectSelectedReducer from './projectSelectedReducer';

import projectTaskReducer from './projectTaskReducer';
import projectTaskSelectedReducer from './projectTaskSelectedReducer';
import securityReducer from './securityReducer';
import userInfoReducer from './userInfoReducer';

export default combineReducers({
    errors: errorReducer,
    projects: projectReducer,
    project: projectSelectedReducer,
    projectTask: projectTaskSelectedReducer,
    projectTasks: projectTaskReducer,
    security: securityReducer,
    userInfo: userInfoReducer
});