export interface IResponseToken {
    "access_token": string;
    "expires_in": number;
    "token_type": string;
    "error"?: boolean;
    "host"?: string;
    "path"?: string;
}
