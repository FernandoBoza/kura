import React from 'react';
import GoogleMapReact from 'google-map-react';

class App extends React.Component {

    state = {
        title: "Hospital services at a glance"
    };

    render() {
        const greatPlaceStyle = {
            position: 'absolute',
            transform: 'translate(-50%, -50%)',
            fontSize: '3rem',
            color: 'red'
        };

        let x = [1,2,3,4,5,6];
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
                        <p className=" intro">Lorem ipsum dolor sit amet, consectetur
                            adipisicing elit. Aperiam
                            corporis expedita
                            minima omnis perspiciatis
                            quasi quibusdam quis reiciendis repellendus ut! Consequatur illo inventore ipsum iure nobis
                            porro quasi reiciendis
                            vitae.</p>

                        <div className="wave_blocks">
                            <div className=" block blue">
                                <p>Hospitals</p>
                                <img src="../assets/blue.svg" alt=""/>
                            </div>
                            <div className=" block purple">
                                <p>Services</p>
                                <img src="../assets/purple.svg" alt=""/>
                            </div>
                        </div>


                    </section>
                    <section className=" right-col col">
                        <GoogleMapReact
                            bootstrapURLKeys={{key: " AIzaSyB7XZM9ZU0jM3SAnFxfLes_8OXOQ0ugI9I"}}
                            defaultCenter={[25.7617, -80.1918]} defaultZoom={12}>
                            {x.map( t => {
                                return <div style={greatPlaceStyle}  lat={25.7617} lng={-80.1918}>{t}</div>
                            })}
                        </GoogleMapReact>
                    </section>
                </section>
            </section>
        );
    }
}

export default App;
