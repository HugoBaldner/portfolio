function  impairment(FileName,SNR)
[x,Fs] = audioread(strcat(FileName,'.wav'));
x = resample(x,16640,Fs);

 %z=awgn(y,SNR,'measured');
 sigp = 10*log10(norm(x,2)^2/numel(x));
 snr = sigp-SNR;
 noisep = 10^(snr/10);
 noise = sqrt(noisep)*randn(size(x));
 z = x+noise;
 z = resample(z,16000,16640);

audiowrite(strcat(num2str(SNR),FileName,'.wav'),z,16000);


