import React, { Component } from 'react';
import { connect } from 'react-redux';
import PropTypes from 'prop-types';
import { getProject, createProject } from './../../actions/projectActions';
import classnames from 'classnames';

class UpdateProject extends Component {
    constructor(props) {
        super(props);

        this.state = {
            projectId: "",
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
        let value  = event.target.value;
        let name   = event.target.name;

        this.setState({
            [name]: value
        });
    }

    handleSubmit(event) {
        event.preventDefault();

        let updateProject = {
            projectId: this.state.projectId,
            projectName: this.state.projectName,
            projectIdentifier: this.state.projectIdentifier,
            description: this.state.description,
            startDate: this.state.startDate,
            endDate: this.state.endDate
        };
      
        this.props.createProject(updateProject, this.props.history);
    }

    componentWillMount(){
        const {id} = this.props.match.params;
        
        this.props.getProject(id, this.props.history);
    }

    componentWillReceiveProps(nextProps){
        //console.log(nextProps);
        if(nextProps.project) {
            this.updateProject(nextProps.project);
        }
       
        if(nextProps.errors) {
            this.setState({errors: nextProps.errors});
        }
    }

    updateProject(project){
        this.setState({
            projectId: project.projectId,
            projectName: project.projectName,
            projectIdentifier: project.projectIdentifier,
            description: project.description,
            startDate: project.startDate,
            endDate: project.endDate,
        });
    }

    render() {
        const {errors} = this.state;

        return (
            <div className="register">
                
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <h5 className="display-4 text-center">Update Project Form</h5>
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
                                    {
                                        errors.projectName && (
                                        <div className="invalid-feedback">{errors.projectName}</div>
                                    )}
                                    
                                </div>
                                <div className="form-group">
                                    <input 
                                        type="text" 
                                        name="projectIdentifier" 
                                        onChange={this.handleChange} 
                                        value={this.state.projectIdentifier} 
                                        className="form-control form-control-lg"
                                        placeholder="Unique Project ID" disabled
                                         />
                                    
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
                                    {
                                        errors.description && (
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

UpdateProject.propTypes = {
    getProject: PropTypes.func.isRequired,
    createProject: PropTypes.func.isRequired,
    project: PropTypes.object.isRequired,
    errors: PropTypes.object.isRequired
}

const mapStateToProps = state => ({
    project: state.project,
    errors: state.errors
});

export default connect(mapStateToProps, {getProject, createProject})(UpdateProject);
