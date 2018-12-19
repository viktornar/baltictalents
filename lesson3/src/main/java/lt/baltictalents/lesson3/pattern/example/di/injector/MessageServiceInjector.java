package lt.baltictalents.lesson3.pattern.example.di.injector;

import lt.baltictalents.lesson3.pattern.example.di.client.Client;

public interface MessageServiceInjector {
	Client getClient();
}
