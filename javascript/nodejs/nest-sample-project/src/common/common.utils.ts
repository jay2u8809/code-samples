
export function isEmpty(obj: any): boolean {

    if (obj === null) {
        return true;
    }

    if (obj === undefined) {
        return true;
    }

    if (obj instanceof Array) {
        return obj.length === 0;
    }

    if (obj instanceof Map) {
        return obj.size === 0;
    }

    if (obj instanceof Set) {
        return obj.size === 0;
    }

    if (obj instanceof Buffer) {
        return obj.length === 0;
    }


    // else
    return false;
}
