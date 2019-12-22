import * as types from './../actions/types';

const initialState = {
    projectTasks: []
};

export default function(state = initialState, action) {
    switch(action.type) {
        case types.GET_PROJECT_TASKS:
            return {
                projectTasks: action.payload
            };
      
        case types.DELETE_PROJECT_TASK:
            return {
                projectTasks: state.projectTasks.filter(
                    task => task.projectSequence !== action.payload
                )};
        default:
            return state;
    }
}