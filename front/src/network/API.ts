import {Filter} from '../components/BusinessTableFilter/BusinessTableFilter';


interface Request {
    id: string,
    concierge: string,
    site: string,
    typeRequest: string,
    requestDesc: string,
    numberPerson: string,
    place: string,
    regularity: string,
    duration: string,
    material: boolean [],
    materialother: string,
    internalInfo: string,
    executionDate: string,
    executionTime: string,
    requestStatus: string,
    requestAssignment: string,
    images: File [],
    photos: string[],
    client: IClient
}

interface IClient {
    clientStatus: string,
    company: string,
    gender: string,
    lName: string,
    fName: string,
    phone: string,
    email: string,
    address: string,
    cp: string,
    city: string
    siret: string
}

export interface BackendRequest {
    id: number;
    date: Date;
    site: string;
    responsable: backendClosedBy;
    client: backendClient;
    priority: string;
    description: string;
    status: string;
    closedBy: backendClosedBy;
    accessDetails: string;
    repetitionTime: number;
    repetitionUnit: string;
    type: string;
    amountWood: number;
    amountDonated: number;
    appointmentPlasmaDate: Date;
    estimation: backendEstimation;
    satisfactionLevel: string;
    lastUpdated: Date;
    lastUpdatedBy: backendClosedBy;
    photos: string[];
}

export interface backendClient {
    id: number;
    email: string;
    phoneNumber: string;
    address: string;
    postCode: string;
    city: string;
    companyName: string;
    lastName: string;
    firstName: string;
    honorificTitle: string;
    type:string;
}

export interface backendClosedBy {
    id: number;
    username: string;
}

export interface backendEstimation {
}


interface User {
    id: string;
    username: string;
    authorities: string[];
}

const API = {
    login: async function (username: string, password: string): Promise<boolean> {
        let found = false;

        const requestOptions = {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify({username: username, password: password})
        };
        await fetch('/auth/authenticate', requestOptions)
            .then(async response => {
                const data = await response.json();
                if (!response.ok) {
                    const error = (data && data.message) || response.status;
                    found = false;
                    return Promise.reject(error);
                } else {
                    const token = data.token
                    localStorage.setItem('token', token)
                    const refreshToken = data.refreshtoken
                    localStorage.setItem('refreshToken', refreshToken)
                    var role: string = "";
                    const temp = data.authorities[0]
                    if (temp !== undefined) {
                        role = temp.authority;
                    } else {
                        role = ""
                    }
                    localStorage.setItem('role', role)
                    found = true;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return found;
    },


    disconnect: async function (): Promise<any> {

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;

        temp = localStorage.getItem('refreshToken');
        if (temp === null) {
            temp = "";
        }
        let refreshToken: string = temp;
        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({refreshToken: refreshToken})
        };

        await fetch('/auth/token', requestOptions)
            .then(async response => {
                if (response.status !== 204) {
                    return Promise.reject(response);
                } else {
                    localStorage.setItem('token', "")
                    localStorage.setItem('refreshToken', "")
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


    },

    getRole: function (): string {
        var ret = localStorage.getItem('role')
        if (ret === null) {
            ret = ""
        }
        return ret;
    },


    addRequest: async function (request: Request): Promise<boolean> {

        let ret = false;
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({
                "site": request.site,

                "client": {
                    email: request.client.email,
                    phoneNumber: request.client.phone,
                    address: request.client.address,
                    postCode: request.client.cp,
                    city: request.client.city,
                    companyName: request.client.company,
                    lastName: request.client.lName,
                    firstName: request.client.fName,
                    honorificTitle: "Mr",
                    type: request.client.clientStatus
                },
                "priority": "Basse",
                "description": request.requestDesc,
                "repetitionTime": +request.regularity,
                "date": request.executionDate,
                "type": request.typeRequest,
                "responsable": {"username": localStorage.getItem('username')},
                "status": request.requestStatus,
                "accessDetails": request.place
            })
        };

        await fetch('/requests/create', requestOptions)
            .then(async response => {
                if (response.status !== 201) {
                    return Promise.reject(response);
                } else {
                    const data: BackendRequest = await response.json();
                    ret = true;
                    if (request.images.length > 0)
                        this.uploadFile(request.images, data.id.toString())
                    console.log(data)
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return ret;

    },
    getRequests: async function (filter: Filter): Promise<Request[]> {

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({})
        };
        var res: Request[] = []
        await fetch('/requests', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    const data = await response.json();
                    res = data.map((request: BackendRequest) => (this.backendRequestToFrontendRequest(request)));
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })

        return res;
        //return requests.filter((request => request.site.toLocaleLowerCase().includes(filter.site.toLocaleLowerCase())))
    },

    photosAddressesToFiles: async function (addresses: string[]): Promise<File[]> {
        const ret: File[] = []
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        for (let i = 0; i < addresses.length; i++) {
            await fetch(addresses[i], { // Your POST endpoint
                method: 'GET',
                headers: {
                    'Authorization': "Bearer " + token
                }
            }).then(r => r.blob())
                .then(blobFile => {
                    var temp: File = new File([blobFile], "img" + i, {type: "image/jpeg"});
                    ret.push(temp);
                });
        }


        return ret;
    },

    backendRequestToFrontendRequest: function (request: BackendRequest): Request {
        console.log(request)
        const date1 = new Date(2021, 1, 27);
        var client: IClient;

        if (request.client !== null) {
            client = {
                clientStatus: request.client.type,
                company: request.client.companyName,
                gender: request.client.honorificTitle,
                lName: request.client.lastName,
                fName: request.client.firstName,
                phone: request.client.phoneNumber,
                email: request.client.email,
                address: request.client.address,
                cp: request.client.postCode,
                city: request.client.city,
                siret: "XXXX"
            }
        } else {
            client = {
                clientStatus: "",
                company: "",
                gender: "",
                lName: "",
                fName: "",
                phone: "",
                email: "",
                address: "",
                cp: "",
                city: "",
                siret: ""

            }
        }

        var tempDate: Date = date1
        if (request.date !== null) {
            tempDate = request.date
        }
        var tempResponsable: string
        if (request.responsable !== null) {
            tempResponsable = request.responsable.username
        } else {
            tempResponsable = ""
        }
        var retRequest: Request = {
            id: request.id.toString(),
            concierge: tempResponsable,
            site: request.site,
            requestStatus: request.status,
            requestAssignment: "",
            typeRequest: request.type,
            requestDesc: request.description,
            numberPerson: "",
            place: request.accessDetails,
            regularity: request.repetitionTime.toString(),
            duration: "",
            internalInfo: "",
            images: [],
            client: client,
            photos: request.photos,
            executionTime: "",
            executionDate: request.date.toString(),
            material: [],
            materialother: "",


        }

        return retRequest;

    },


    uploadFile(files: File[], id: string) {
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const formData = new FormData();

        files.forEach((file) => {
            formData.append('images', file)
        });

        fetch('/requests/' + id, { // Your POST endpoint
            method: 'POST',
            headers: {
                'Authorization': "Bearer " + token
            },
            body: formData // This is your file object
        }).then(
            response => console.log(response) // if the response is a JSON object
        ).then(
            success => console.log(success) // Handle the success response object
        ).catch(
            error => console.log(error) // Handle the error response object
        );
    },

    getRequest: async function (id: string): Promise<any> {

        var ret = null
        // eslint-disable-next-line
        if (id == "-1")
            return null;

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
        };

        await fetch('/requests/' + id, requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    const data: BackendRequest = await response.json();
                    var temp: Request = await this.backendRequestToFrontendRequest(data)
                    console.log(temp)
                    ret = temp;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })

        return ret;


    },


    getUsers: async function (): Promise<User[]> {
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
        };


        let ret: User[] = []
        await fetch('/users', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    const data = await response.json();
                    ret = data;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return ret;
    },

    addUser: async function (username: string, password: string, role: string): Promise<any> {

        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({username: username, password: password, authorities: [role]})
        };

        await fetch('/register', requestOptions)
            .then(async response => {
                if (!response.ok) {
                    return Promise.reject(response);
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })


    },


    removeUserByUsername: async function (username: string): Promise<boolean> {
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify({username: username})
        };
        let ret = false;
        await fetch('/removeUser', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    ret = true;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return ret;
    },

    getUsername() {
        return localStorage.getItem('username');
    },

    getRequestStatus() {
        return ["Nouvelle", "En cours", "Devis signé", "Doublon", "Facturée", "Refusée", "Clôturée"];
    },

    getServices() {
        return ["Bois", "Couture", "Tri", "Recyclerie", "Enlèvements", "Estimateur"];
    },

    getUrgencyStatus() {
        return ["Normale", "Alerte orange", "Alerte Rouge"]
    }


}

export default API
