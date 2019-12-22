import * as types from './../actions/types';

const initialState = {
    user: {},
    validToken: false
};

export default function(state = initialState, action) {
    switch(action.type) {
        case types.SET_CURRENT_USER:
            return {
                user: action.payload,
                validToken: true
            };
        case types.LOGOUT:
            return {
                user: action.payload,
                validToken: false
            };
            
        default:
            return state;
    }
}