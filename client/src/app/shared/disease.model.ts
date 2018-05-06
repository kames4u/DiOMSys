export interface Disease {
    diseaseID: number;
    diseaseName?: string;
    occuredCity?: string;
    occuredState?: string;
    occuredMonth?: number;
    occuredYear?: number;
    noOfIllness?: number;
    noOfHospitalized?: number;
    noOfDeath?: number;
    dataSource?: string;
    category?: string;
    status?: string;
}
