import React, { Component } from 'react';

import CreateProjectButton from './Project/CreateProjectButton';
import { connect } from 'react-redux';
import { getProjects } from './../actions/projectActions';
import PropTypes from 'prop-types';
import ProjectItem from './Project/ProjectItem';

class Dashboard extends Component {
    
    componentWillMount(){
        this.props.getProjects();
    }

    render() {
        const {projects} = this.props;

        return (
            <div className="projects">
                <div className="container">
                    <div className="row">
                        <div className="col-md-12">
                            <h1 className="display-4 text-center">Projects</h1>
                            <br />
                            <CreateProjectButton />
                            <br />
                            <hr />

                            {projects.map(project => (
                                <ProjectItem key={project.projectId} project={project} />
                            ))}                     
                            
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

Dashboard.propTypes = {
    getProjects: PropTypes.func.isRequired,
    projects: PropTypes.array.isRequired
}

const mapStateToProps = state => ({
    projects: state.projects.projects
});

export default connect(mapStateToProps, {getProjects})(Dashboard);

