package pl.mbos.aidl.service;

/** Example service interface */
import pl.mbos.aidl.MyObject;

interface IExampleService {

    String customObject(in MyObject object);

}