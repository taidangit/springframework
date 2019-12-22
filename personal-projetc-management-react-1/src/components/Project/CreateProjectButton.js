import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class CreateProjectButton extends Component {
    render() {
        return (
            <React.Fragment>
                 <Link to="/addNewProject" className="btn btn-info btn-lg">
                    <i className="fa fa-plus-circle mr-1"></i>Create new project
                </Link>
            </React.Fragment>
           
        );
    }
}

export default CreateProjectButton;
