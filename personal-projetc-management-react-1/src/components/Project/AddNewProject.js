import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { createProject } from './../../actions/projectActions';
import classnames from 'classnames';

class AddNewProject extends Component {
    constructor(props) {
        super(props);

        this.state = {
            projectName: "",
            projectIdentifier: "",
            description: "",
            startDate: "",
            endDate: "",
            errors: {}
        };

        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event) {
        const value  = event.target.value;
        const name   = event.target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();
        
        const newProject = {
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            startDate: this.state.startDate,
            endDate: this.state.endDate
        };
        //console.log(newProject);
        this.props.createProject(newProject, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        console.log(nextProps);
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    render() {
        const {errors} = this.state;

        return (
            
            <div className="register">
                
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Create Project Form</h5>
                            <hr />
                            <form onSubmit={this.handleSubmit}>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        name="projectName" 
                                        onChange={this.handleChange} 
                                        value={this.state.projectName} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.projectName
                                        })} 
                                        placeholder="Project Name" />
                                    {errors.projectName && (
                                        <div className="invalid-feedback">{errors.projectName}</div>
                                    )}
                                </div>

                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        name="projectIdentifier" 
                                        onChange={this.handleChange} 
                                        value={this.state.projectIdentifier} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.projectIdentifier
                                        })} 
                                        placeholder="Unique Project ID"
                                         />
                                    {errors.projectIdentifier && (
                                        <div className="invalid-feedback">{errors.projectIdentifier}</div>
                                    )}
                                </div>
                               
                                <div className="form-group">
                                    <textarea 
                                        name="description" 
                                        onChange={this.handleChange} 
                                        value={this.state.description} 
                                        className={classnames("form-control form-control-lg", {
                                            "is-invalid": errors.description
                                        })} 
                                        placeholder="Project Description"></textarea>
                                    {errors.description && (
                                        <div className="invalid-feedback">{errors.description}</div>
                                    )}
                                </div>
                                <h6>Start Date</h6>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        name="startDate" 
                                        onChange={this.handleChange} 
                                        value={this.state.startDate} 
                                        className="form-control form-control-lg" />
                                </div>
                                <h6>Estimated End Date</h6>
                                <div className="form-group">
                                    <input 
                                        type="date" 
                                        name="endDate" 
                                        onChange={this.handleChange} 
                                        value={this.state.endDate} 
                                        className="form-control form-control-lg" />
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
           
        );
    }
}

AddNewProject.propTypes = {
    createProject: PropTypes.func.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    errors: state.errors
});

export default connect(mapStateToProps, {createProject})(AddNewProject);
