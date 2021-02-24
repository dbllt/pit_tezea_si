import {Filter} from '../components/BusinessTableFilter/BusinessTableFilter';
import emailjs from "emailjs-com";


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
    material: string [],
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
    internalInfo:string,
    photos: string[],
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
    type: string;
}

export interface backendClosedBy {
    id: number;
    username: string;
}

export interface backendEstimation {
    numberEmployeesNeeded:number,
    expectedDuration:number,
    toolsNeeded:string[]
}

export interface PatchRequest {
    id?: number;
    date?: string;
    site?: string;
    responsable?: PatchClosedBy;
    client?: PatchClient;
    priority?: string;
    description?: string;
    status?: string;
    closedBy?: PatchClosedBy;
    accessDetails?: string;
    repetitionTime?: number;
    repetitionUnit?: string;
    type?: string;
    amountWood?: number;
    amountDonated?: number;
    appointmentPlasmaDate?: Date;
    estimation?: PatchEstimation;
    satisfactionLevel?: string;
    lastUpdated?: Date;
    lastUpdatedBy?: PatchClosedBy;
    photos?: string[];
    internalInfo?:string
}

export interface PatchClient {
    id?: number;
    email?: string;
    phoneNumber?: string;
    address?: string;
    postCode?: string;
    city?: string;
    companyName?: string;
    lastName?: string;
    firstName?: string;
    honorificTitle?: string;
    type?: string;
}

export interface PatchClosedBy {
    id?: number;
    username?: string;
}

export interface PatchEstimation {
    numberEmployeesNeeded?:number,
    expectedDuration?:number,
    toolsNeeded?:string[]
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
                    localStorage.setItem('hourOfToken', new Date().getFullYear().toString())


                    if (data.authorities.length > 1 && data.authorities[1].authority === "Responsable Site") {
                        localStorage.setItem('site', data.authorities[0].authority)
                        let role: string = "";
                        const temp = data.authorities[1]
                        if (temp !== undefined) {
                            role = temp.authority;
                        } else {
                            role = ""
                        }
                        localStorage.setItem('role', role)
                    } else {

                        let role: string = "";
                        const temp = data.authorities[0]
                        if (temp !== undefined) {
                            role = temp.authority;
                        } else {
                            role = ""
                        }
                        localStorage.setItem('role', role)
                    }

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
                    localStorage.setItem('role', "")
                    localStorage.setItem('site', "")
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

    formatDate: function (date: string): string {
        var t = date.split("-");
        var ret = t[2] + "-" + t[1] + "-" + t[0]
        return ret;

    },

    addRequest: async function (request: Request): Promise<boolean> {

        let ret = false;
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;
        if(request.typeRequest==="Prestation"){
            this.sendEmail()
        }

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
                    honorificTitle: request.client.gender,
                    type: request.client.clientStatus
                },
                "priority": "Basse",
                "description": request.requestDesc,
                "repetitionTime": +request.regularity,
                "date": this.formatDate(request.executionDate),
                "type": request.typeRequest,
                "responsable": {"username": localStorage.getItem('username')},
                "status": "Nouveau",
                "accessDetails": request.place,
                "internalInfo":request.internalInfo,
                "estimation": {
                    "numberEmployeesNeeded":request.numberPerson,
                    "expectedDuration":request.duration,
                    "toolsNeeded":request.material
                }
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

    editRequest: async function (request:PatchRequest): Promise<boolean> {
        let ret = false;
        let temp = localStorage.getItem('token');
        if (temp === null) {
            temp = "";
        }
        let token: string = temp;


        const requestOptions = {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': "Bearer " + token
            },
            body: JSON.stringify(request)
        };

        await fetch('/requests/', requestOptions)
            .then(async response => {
                if (response.status !== 200) {
                    return Promise.reject(response);
                } else {
                    const data: BackendRequest = await response.json();
                    ret = true;
                    console.log(data)
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return ret;

    },


    convertDate(d: any) {
        if (d !== null) {
            let day: string = ("0" + d.getDate()).slice(-2)
            let month: string = ("0" + (d.getMonth() + 1)).slice(-2)
            return day + "-" + month + "-" + d.getFullYear()
        }
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
            body: JSON.stringify({
                "client": {
                    "postCode": "",
                    "lastName": filter.clientName,
                    "phoneNumber": filter.phoneNumber,
                    "city": filter.localization
                },
                "startDate": this.convertDate(filter.startDate),
                "endDate": this.convertDate(filter.endDate),
                "site": filter.site === "" ? undefined : filter.site,
                //"type": filter.requestObject, TODO
                "status": filter.requestStatus === "" ? undefined : filter.requestStatus
                //TODO urgence a faire coté front
            })
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
            numberPerson: request.estimation.numberEmployeesNeeded.toString(),
            place: request.accessDetails,
            regularity: request.repetitionTime.toString(),
            duration: request.estimation.expectedDuration.toString(),
            internalInfo: request.internalInfo,
            images: [],
            client: client,
            photos: request.photos,
            executionTime: "",
            executionDate: request.date.toString(),
            material: request.estimation.toolsNeeded,
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

    addUser: async function (username: string, password: string, role: string, site: string): Promise<boolean> {

        let success = false;
        var authorities = [];
        authorities[0] = role;
        if (role === "Responsable Site") {
            authorities[1] = site;
        }

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
            body: JSON.stringify({username: username, password: password, authorities: authorities})
        };

        await fetch('/register', requestOptions)
            .then(async response => {
                if (!response.ok) {
                    return Promise.reject(response);
                } else {
                    success = true;
                }
            }).catch(error => {
                console.error('There was an error!', error);
            })
        return success;

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

    getSite() {
        return localStorage.getItem('site') || "";
    },

    getRequestStatus() {
        return ["En cours", "Nouveau", "Devis signé", "Doublon", "Facturé", "Refusé", "Clôturé", "Client a appelé", "Devis en cours", "Autre"];
    },

    getServices() {
        return [
            "Bois", "Couture", "Tri démantèlement", "Recyclerie", "Dons enlèvements", "Estimateur", "Conciergerie"];
    },

    getUrgencyStatus() {
        return ["Normale", "Alerte orange", "Alerte Rouge"]
    },

    sendEmail() {
        emailjs.init("user_xXE8w4OznmdPxlbf8cIz6");
        emailjs.send('tezea', 'mail', {}, 'user_xXE8w4OznmdPxlbf8cIz6')
            .then((result) => {
                console.log(result.text);
            }, (error) => {
                console.log(error.text);
            });
    }

}

export default API
