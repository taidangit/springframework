import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getProjectTasks } from './../../actions/projectTaskActions';

import Backlog from './Backlog';

class ProjectBoard extends Component {

    constructor(props) {
        super(props);

        this.state = {
            errors: {}
        };
    }

    componentWillMount(){
        //console.log("componentWillMount");
        const {id} = this.props.match.params;
        this.props.getProjectTasks(id, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        //console.log(nextProps);
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    render() {
        const {id} = this.props.match.params;
        const {projectTasks} = this.props;
        const {errors} = this.state;
        
        let boardContent = null; 

        const boardAlgorithm = (errors, projectTasks) => {
            if(projectTasks.length === 0) {
                if(errors.projectNotFound) {
                    return (
                        <div className="alert alert-danger text-center" role="alert">
                            {errors.projectNotFound}
                        </div>
                    );
                } else if(errors.projectIdentifier) {
                    return (
                        <div className="alert alert-danger text-center" role="alert">
                            {errors.projectIdentifier}
                        </div>
                    );
                } else {
                    return (
                        <div className="alert alert-info text-center" role="alert">
                            No project tasks on this board.
                        </div>
                    );
                }
            } else {
                return (
                    <Backlog projectTasks={projectTasks} />
                );
            }
            
        }

        boardContent = boardAlgorithm(errors, projectTasks);

        return (
            <div className="container">
                <Link to={`/addProjectTask/${id}`} className="btn btn-primary mb-3">
                    <i className="fa fa-plus-circle mr-1"></i>Create Project Task
                </Link>
                <br />
                <hr />

                {boardContent}

            </div>
           
        )
    }
}

ProjectBoard.propTypes = {
    getProjectTasks: PropTypes.func.isRequired,
    projectTasks: PropTypes.array.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    projectTasks: state.projectTasks.projectTasks,
    errors: state.errors
});

export default connect(mapStateToProps, {getProjectTasks})(ProjectBoard);
