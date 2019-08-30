import React from 'react';
import GoogleMapReact from 'google-map-react';
import Marker from "./components/Marker";
import SearchBar from "./components/SearchBar";
import axios from "axios";
import Result from "./components/Results";
import {ProcedureLayout} from "./components/ProcedureLayout"

class App extends React.Component {


    state = {
        hospitals: [],
        title: "Hospital services at a glance",
        lat: 25.7617,
        lng: -80.1918,
        radius: 12,
        hospSelected: [],
        procedures: [],
        hideMap: false
    };

    getZipcodeCoordinates = (zipcode, radius) => {
        let zipSearch = `https://maps.googleapis.com/maps/api/geocode/json?address=${zipcode}&key=AIzaSyB7XZM9ZU0jM3SAnFxfLes_8OXOQ0ugI9I`;
        axios.get(zipSearch)
            .then(res => {
                let {lat, lng} = res.data['results'][0].geometry.location;
                this.findAllHospitals(radius, lat, lng)
            })
    };

    calcRadius = radius => {
        if (radius <= 5) {
            return 16;
        } else if (radius <= 10) {
            return 12;
        } else if (radius <= 20) {
            return 10
        }
    };

    findAllHospitals = (radius, lat, lng) => {
        axios.get(`http://localhost:8080/hospitals/v1/hosp/search/${radius}_${lat}_${lng}`)
            .then(res => {
                this.setState({
                    hospitals: res.data,
                    lat: lat,
                    lng: lng,
                    radius: this.calcRadius(radius)
                });
            })
            .catch(err => console.log(err));
    };

    hospitalSelect = hospital => {

        this.setState({
            hospSelected: hospital,
        });
    };

    getAllProcedures = hospital => {
        console.log("clicked");
        this.setState({
            hideMap: true,
            procedures: hospital.procedures,
        });
    };

    render() {

        const hide = this.state.hideMap;
        return (
            <section className="app">
                <nav>
                    <h3 className="kura">Kura.</h3>
                    <ul>
                        <li>Contact</li>
                        <li>About</li>
                    </ul>
                </nav>
                <section className="main">
                    <section className="left-col col">
                        <h1>{this.state.title}</h1>
                        <p className=" intro">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aperiam corporis
                            expedita minima omnis perspiciatis quasi quibusdam quis reiciendis repellendus ut!
                            Consequatur illo inventore ipsum iure nobis porro quasi reiciendis vitae.</p>


                        <SearchBar search={this.getZipcodeCoordinates}/>

                        <Result hospitals={this.state.hospitals} lat={this.state.lat} lng={this.state.lng}
                                selected={this.state.hospSelected} hospitalSelect={this.hospitalSelect}
                                getAllProcedures={this.getAllProcedures}
                        />


                    </section>
                    <section className={hide ? 'right-col col query' : 'right-col col'}>

                        <ProcedureLayout class={hide ? 'show' : 'hide'} procedures={this.state.procedures}
                                         hospital={this.state.hospSelected}/>

                        <section className={!hide ? 'show' : 'hide'}>
                            <GoogleMapReact
                                bootstrapURLKeys={{key: " AIzaSyB7XZM9ZU0jM3SAnFxfLes_8OXOQ0ugI9I"}}
                                center={[this.state.lat, this.state.lng]} zoom={this.state.radius}
                            >
                                {this.state.hospitals.map(h => {
                                    return <Marker key={h.id} lat={h.lat} lng={h.lng} selected={this.state.hospSelected}
                                                   hospitalSelect={this.hospitalSelect} hosp={h}/>
                                })}
                            </GoogleMapReact>
                        </section>

                    </section>
                </section>
            </section>
        );
    }
}

export default App;
